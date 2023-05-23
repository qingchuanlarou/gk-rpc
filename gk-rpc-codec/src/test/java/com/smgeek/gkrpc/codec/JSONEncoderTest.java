package com.smgeek.gkrpc.codec;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();

        TestBean testBean = new TestBean();
        testBean.setName("hanbiao");
        testBean.setAge(24);

        byte[] bytes = encoder.encode(testBean);
        assertNotNull(bytes);
    }
}