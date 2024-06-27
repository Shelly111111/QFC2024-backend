package com.qunar.qfc2024.web.socketcomm.server;


import com.qunar.qfc2024.common.utils.CharacterUtil;
import com.qunar.qfc2024.api.vo.WebInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * web服务
 *
 * @author zhangge
 * @date 2024/6/21
 */
@Slf4j
public class WebServer {

    public static final String URL = "https://blog.csdn.net/qq_35151259/article/details/136358566";


    /**
     * 根据url获取页面信息
     *
     * @param url 链接地址
     * @return 页面信息
     * @throws IOException
     * @author zhangge
     * @date 2024/6/21
     */
    private static WebInfo getWebInfo(String url) throws IOException {

        //总字符数
        Long characterCount = 0L;
        //汉字数
        Long chineseCharacterCount = 0L;
        //英文字数
        Long englishCharacterCount = 0L;
        //标点符号数
        Long punctuationMarksCount = 0L;

        URLConnection connection = new URL(url).openConnection();
        //建立链接
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        //循环扫描
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            //统计字符数
            for (char c : line.toCharArray()) {
                //过滤空白字符
                if (CharacterUtil.isBlank(c)) {
                    continue;
                }

                if (CharacterUtil.isChinese(c)) {
                    chineseCharacterCount++;
                } else if (CharacterUtil.isEnglish(c)) {
                    englishCharacterCount++;
                } else if (CharacterUtil.isPunctuation(c)) {
                    punctuationMarksCount++;
                }
                characterCount++;
            }
//            System.out.println(line);
        }
        WebInfo webInfo = new WebInfo();
        webInfo.setUrl(url);
        webInfo.setCharacterCount(characterCount);
        webInfo.setChineseCharacterCount(chineseCharacterCount);
        webInfo.setEnglishCharacterCount(englishCharacterCount);
        webInfo.setPunctuationMarksCount(punctuationMarksCount);
        return webInfo;
    }

    public static void main(String[] args) {

        NioEventLoopGroup group = new NioEventLoopGroup();

        //服务端启动器，负责组装netty组件
        ChannelFuture channelFuture = new ServerBootstrap()
                //事件循环组
                .group(group)
                //选择服务器的 ServiceSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                //child负责读写，实现业务逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                        //将 ByteBuf 转为字符串
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        //自定义通道读取handler
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                ctx.fireChannelRead(msg);
                                log.info("收到客户端消息：{}", msg);
                                WebInfo webInfo = getWebInfo((String) msg);
                                ctx.writeAndFlush(webInfo.toString());
                            }
                        });
                    }
                }).bind(8090);

        //处理关闭后的操作
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();

        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                log.info("处理关闭后的操作");
                group.shutdownGracefully();
            }
        });
//        channelFuture.channel().close();
    }
}
