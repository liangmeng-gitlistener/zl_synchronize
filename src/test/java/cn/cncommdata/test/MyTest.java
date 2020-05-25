package cn.cncommdata.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * 购物车测试类
 */
public class MyTest {
    @Test
    void test1(){
        Product product1 = new Product();
        product1.setPrice(200D);
        product1.setQuantity(1);
        Product product2 = new Product();
        product2.setPrice(100D);
        product2.setQuantity(1);
        List list = CollUtil.newArrayList(product1, product2);
        System.out.println(TotalAmountOfShoppingCart(list));

        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("e:/qrcode.jpg"));
    }

    public double TotalAmountOfShoppingCart(List<Product> cartEventStream) {
        double result = cartEventStream.stream().filter(Objects::nonNull)
                // 分别计算每类商品金额
                .map(v -> v.getPrice() * v.getQuantity())
                // 计算每类商品满减后的商品金额
                .map(v -> (v > 199) ? (v - 40) : v)
                .mapToDouble(Double::doubleValue).sum();
        // 根据result判断是否免邮，得到最终总付款金额
        return (result > 500) ? result : (result + 50);
    }

    @Data
    static class Product{
        private Double price;
        private Integer quantity;
    }
}