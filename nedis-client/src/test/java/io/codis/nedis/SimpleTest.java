package io.codis.nedis;

import io.codis.nedis.util.NedisUtils;
import io.netty.util.concurrent.Future;
import org.junit.Test;

import java.net.InetSocketAddress;

import static io.codis.nedis.util.NedisUtils.bytesToString;
import static io.codis.nedis.util.NedisUtils.toBytes;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SimpleTest {


    private static int PORT = 80;

    //private static RedisServer REDIS;

    private NedisClientPool pool;

    private String hostName = "10.2.12.235";

    @Test
    public void test()throws Exception{

        long startTime = System.currentTimeMillis();

        pool = NedisClientPoolBuilder.create()
                        .remoteAddress(new InetSocketAddress(hostName, PORT)).maxPooledConns(1).build();
        NedisClient client = NedisUtils.newPooledClient(pool);
        //设置密码
        client.auth(toBytes("111"));
        System.out.println(client.toString());
        Future<String> pingFuture = client.ping();
        for (int i = 0; i < 10000; i++) {
            Future<Boolean> setFuture = client.set(toBytes("foo"), toBytes("bar" + i));
            //assertTrue(setFuture.sync().getNow());
            //assertEquals("bar" + i, bytesToString(client.get(toBytes("foo")).sync().getNow()));
        }

        System.out.println(bytesToString(client.get(toBytes("foo")).sync().getNow()));

        long endTime = System.currentTimeMillis();

        System.out.println(endTime-startTime);
    }
}
