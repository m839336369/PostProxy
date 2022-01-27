package com.example.PostProxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CureApiApplicationTests {


    @Test
    void contextLoads() {
        final String regex = "\"描述价格\": \"(\\d*)\"";
        final String string = "{\n"
                + "	\"code\": 200,\n"
                + "	\"msg\": \"获取成功！\",\n"
                + "	\"sum\": \"共搜索到[10]条相关信息\",\n"
                + "	\"data\": [\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12(4+64GB)\",\n"
                + "			\"机型图片\": \"http://imgm4.cnmo.com/cnmo_product/23_240x180/414/cefYygA1E83c2.jpg\",\n"
                + "			\"上市时间\": \"2020年10月\",\n"
                + "			\"描述价格\": \"2599.5\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.1英寸; 后置相机：1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：4GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 Pro(6+128GB)\",\n"
                + "			\"机型图片\": \"http://imgm8.cnmo.com/cnmo_product/23_240x180/428/ce7LMHNaZ4M7s.jpg\",\n"
                + "			\"上市时间\": \"2020年10月\",\n"
                + "			\"描述价格\": \"6899\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.1英寸; 后置相机：1200+1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：6GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12(4+256GB)\",\n"
                + "			\"机型图片\": \"http://imgm.cnmo.com/cnmo_product/23_240x180/410/ceGoOamXiGHn6.jpg\",\n"
                + "			\"上市时间\": \"2020年10月\",\n"
                + "			\"描述价格\": \"6399\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.1英寸; 后置相机：1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：4GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12(4+128GB)\",\n"
                + "			\"机型图片\": \"http://imgm2.cnmo.com/cnmo_product/23_240x180/412/ceZtqisJAxLEY.jpg\",\n"
                + "			\"上市时间\": \"2020年10月\",\n"
                + "			\"描述价格\": \"5599\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.1英寸; 后置相机：1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：4GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 Pro Max(6+256GB)\",\n"
                + "			\"机型图片\": \"http://imgm2.cnmo.com/cnmo_product/23_240x180/422/ceIcXZBdPQiTg.jpg\",\n"
                + "			\"上市时间\": \"2020年11月\",\n"
                + "			\"描述价格\": \"8399\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.7英寸; 后置相机：1200+1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：6GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 mini(4+128GB)\",\n"
                + "			\"机型图片\": \"http://imgm8.cnmo.com/cnmo_product/23_240x180/418/ceuYjuSBPhvY.jpg\",\n"
                + "			\"上市时间\": \"2020年11月\",\n"
                + "			\"描述价格\": \"4899\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：5.4英寸; 后置相机：1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：4GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 mini(4+64GB)\",\n"
                + "			\"机型图片\": \"http://imgm.cnmo.com/cnmo_product/23_240x180/420/ceDKdtiStUWBQ.jpg\",\n"
                + "			\"上市时间\": \"2020年11月\",\n"
                + "			\"描述价格\": \"4499\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：5.4英寸; 后置相机：1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：4GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 Pro Max(6+512GB)\",\n"
                + "			\"机型图片\": \"http://imgm6.cnmo.com/cnmo_product/23_240x180/426/cek9eUzP1fSYI.jpg\",\n"
                + "			\"上市时间\": \"2020年11月\",\n"
                + "			\"描述价格\": \"11899\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.7英寸; 后置相机：1200+1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：6GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 Pro Max(6+128GB)\",\n"
                + "			\"机型图片\": \"http://imgm8.cnmo.com/cnmo_product/23_240x180/418/ceBaVJRiIiSEs.jpg\",\n"
                + "			\"上市时间\": \"2020年11月\",\n"
                + "			\"描述价格\": \"7599\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.7英寸; 后置相机：1200+1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：6GB;\"\n"
                + "		},\n"
                + "		{\n"
                + "			\"手机品牌\": \"苹果iPhone12 Pro(6+512GB)\",\n"
                + "			\"机型图片\": \"http://imgm3.cnmo.com/cnmo_product/23_240x180/413/ce6kTDkfx3N3w.jpg\",\n"
                + "			\"上市时间\": \"2020年10月\",\n"
                + "			\"描述价格\": \"9499\",\n"
                + "			\"手机配置\": \" 操作系统：iOS 14 ; 屏幕尺寸：6.1英寸; 后置相机：1200+1200+1200万像素; CPU型号：A14; 核心数：六核; 运行内存：6GB;\"\n"
                + "		}\n"
                + "	],\n"
                + "	\"友情提示\": \"数据来源于手机大全网，交易价格视各地的市场行情而定。配置参数可参观！\"\n"
                + "}";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            System.out.println("完整匹配: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("分组 " + i + ": " + matcher.group(i));
            }
        }
    }

}
