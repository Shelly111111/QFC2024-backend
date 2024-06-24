package com.qunar.qfc2024.web.socketcomm.client;

import com.qunar.qfc2024.common.utils.CharacterUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * web客户端服务
 *
 * @author zhangge
 * @date 2024/6/21
 */
@Slf4j
public class WebClient {

    public static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(4, 4, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static final CountDownLatch COUNT_DOWN = new CountDownLatch(1);

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        //启动客户端
        ChannelFuture channelFuture = new Bootstrap()
                //添加事件循环组
                .group(group)
                //选择客户端channel
                .channel(NioSocketChannel.class)
                //添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        //添加编解码器
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        //自定义通道读取handler
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                ctx.fireChannelRead(msg);
                                //输出打印
                                System.out.println(msg);
                                //唤醒线程
                                COUNT_DOWN.countDown();
                            }
                        });
                    }
                }).connect(new InetSocketAddress("localhost", 8090));

        try {
            Channel channel = channelFuture.sync().channel();
            //开启线程循环读取控制台输入，并将输入发送给服务器
            THREAD_POOL.execute(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("请输入url:");
                    String line = scanner.nextLine();

                    //输入q退出
                    if (CharacterUtil.QUIT.equals(line)) {
                        break;
                    }
                    channel.writeAndFlush(line);
                    try {
                        //线程挂起，等待服务器回传
                        COUNT_DOWN.await();
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                        break;
                    }
                }
                //关闭通道
                channel.close();
            });

            //处理关闭后的操作
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();

        } catch (InterruptedException e) {
            log.error(e.getMessage());
        } finally {
            //关闭线程池
            THREAD_POOL.shutdown();
            //关闭所有事件组
            group.shutdownGracefully();
        }

    }
}
