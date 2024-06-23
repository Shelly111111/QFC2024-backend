package com.qunar.qfc2024.infrastructure.textdecryption;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.qunar.qfc2024.domain.Facade.textdecryption.TextDecryptFacade;
import com.qunar.qfc2024.domain.dto.Prop;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 文本解密
 *
 * @author zhangge
 * @date 2024/6/12
 */
@Component
@Slf4j
public class TextDecryptFacadeImpl implements TextDecryptFacade {

    @Value("${attachments.basePath}")
    private String basePath;

    @Value("${attachments.questions.three.folder}")
    private String folder;

    @Value("${attachments.questions.three.prop}")
    private String prop;

    @Value("${attachments.questions.three.template}")
    private String template;

    @Value("${attachments.questions.three.output}")
    private String output;

    private static final String PATTERN_STR = "\\$[A-Za-z]+\\(\\d+\\)";
    private static final String NATURE_ORDER_STR = "$natureOrder";
    private static final String INDEX_ORDER_STR = "$indexOrder";
    private static final String CHAR_ORDER_STR = "$charOrder";
    private static final String CHAR_ORDER_DESC_STR = "$charOrderDESC";


    /**
     * 获取props
     *
     * @return prop列表，name:索引,value:值
     * @author zhangge
     * @date 2024/6/12
     */
    private List<Prop> getProps() {

        Resource propResource = new ClassPathResource(Paths.get(basePath, folder, prop).toString());
        Integer i = 0;
        try {
            List<Prop> props = Lists.newArrayList();
            //读取prop文件
            InputStream propInputStream = propResource.getInputStream();
            Scanner propScanner = new Scanner(propInputStream);
            while (propScanner.hasNextLine()) {
                String line = propScanner.nextLine();
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                List<String> list = Splitter.on('\t').omitEmptyStrings().trimResults().splitToList(line);
                assert list.size() == 2 : "prop需要索引+内容，索引与内容之间按制表符(tab)分割，且不可存在其他制表符";
                //存储到props中
                Prop param = new Prop();
                param.setNatureOrder(i);
                param.setIndexOrder(Integer.valueOf(list.get(0)));
                param.setValue(list.get(1));
                props.add(param);
                i++;
            }
            //关闭流
            propScanner.close();
            propInputStream.close();

            //进行文本排序和文本倒序
            props = props.parallelStream().sorted(Comparator.comparing(Prop::getValue)).collect(Collectors.toList());
            for (int j = 0; j < props.size(); j++) {
                Prop param = props.get(j);
                param.setCharOrder(j);
                param.setCharOrderDESC(props.size() - j - 1);
            }
            return props;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    /**
     * 解码
     *
     * @param str   加密字符串
     * @param rule  规则
     * @param props props
     * @return 解码后的字符串
     */
    private String decrypt(String str, String rule, List<Prop> props) {
        Prop param;
        if (rule.startsWith(NATURE_ORDER_STR)) {
            //自然排序
            int index = Integer.parseInt(rule.substring(NATURE_ORDER_STR.length() + 1, rule.length() - 1));
            param = props.parallelStream().filter(a -> a.getNatureOrder() == index).findFirst().get();
        } else if (rule.startsWith(INDEX_ORDER_STR)) {
            //索引顺序
            int index = Integer.parseInt(rule.substring(INDEX_ORDER_STR.length() + 1, rule.length() - 1));
            param = props.parallelStream().filter(a -> a.getIndexOrder() == index).findFirst().get();
        } else if (rule.startsWith(CHAR_ORDER_DESC_STR)) {
            //文本倒序
            int index = Integer.parseInt(rule.substring(CHAR_ORDER_DESC_STR.length() + 1, rule.length() - 1));
            param = props.parallelStream().filter(a -> a.getCharOrderDESC() == index).findFirst().get();
        } else if (rule.startsWith(CHAR_ORDER_STR)) {
            //文本排序
            int index = Integer.parseInt(rule.substring(CHAR_ORDER_STR.length() + 1, rule.length() - 1));
            param = props.parallelStream().filter(a -> a.getCharOrder() == index).findFirst().get();
        } else {
            //均无匹配则返回
            return str;
        }
        //替换文本中的相关内容
        str = str.replace(rule, param.getValue());
        return str;
    }

    @Override
    public void textDecrypt() {

        String rootPath = System.getProperty("user.dir");
        //获取props
        List<Prop> props = getProps();
        //读取template文件
        Resource templateResource = new ClassPathResource(Paths.get(basePath, folder, template).toString());

        try {
            //获取输出文件
            File file = new File(Paths.get(rootPath, "out", output).toString());
            if (!file.exists()) {
                file.createNewFile();
            }
            //获取输出流
            OutputStream outputStream = new FileOutputStream(file);
            //获取输入流
            InputStream templateInputStream = templateResource.getInputStream();
            //循环读取并解码
            Scanner templateScanner = new Scanner(templateInputStream);
            Pattern pattern = Pattern.compile(PATTERN_STR);
            while (templateScanner.hasNextLine()) {
                String line = templateScanner.nextLine();
                //匹配规则
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String rule = matcher.group();
                    //解码规则
                    line = decrypt(line, rule, props);
                }
                outputStream.write(line.getBytes());
                outputStream.write('\n');
            }
            outputStream.flush();
            //关闭所有流
            outputStream.close();
            templateScanner.close();
            templateInputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
