package com.smgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();

        TestBean testBean = new TestBean();
        testBean.setName("hanbiao");
        testBean.setAge(24);

        byte[] bytes = encoder.encode(testBean);

        Decoder decoder = new JSONDecoder();
        TestBean testBean1 = decoder.decode(bytes, TestBean.class);
        assertEquals(testBean.getName(),testBean1.getName());
        assertEquals(testBean.getAge(),testBean1.getAge());
    }
}