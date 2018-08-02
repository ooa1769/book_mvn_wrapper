package com.ooa1769.bs.book.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class PriceTest {

    @Test(expected = IllegalArgumentException.class)
    public void 가격_객체_생성_예외() throws Exception {
        Price price = new Price(-1, -1);
        fail();
    }

    @Test
    public void 가격_객체_생성() throws Exception {
        Price price = new Price(34000, 30600);
        assertThat(price.getPrice()).isEqualTo(34000);
        assertThat(price.getSalePrice()).isEqualTo(30600);

        price = new Price(18000, 16200);
        assertThat(price.getPrice()).isEqualTo(18000);
        assertThat(price.getSalePrice()).isEqualTo(16200);
    }

    @Test
    public void 할인율_구하기() throws Exception {
        Price price = new Price(34000, 30600);
        assertThat(price.discountRate()).isEqualTo(10);

        price = new Price(18000, 16200);
        assertThat(price.discountRate()).isEqualTo(10);
    }
}
