package xom.xxt.springcloud;


import com.xxt.springcloud.PaymentApp;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes ={PaymentApp.class} )
public class ZkTest {

    @Autowired
    CuratorFramework curatorFramework;


    /**
     * 测试zooKeeper对zk进行连接与操作
     * @throws Exception
     */
    @Test
    public void testAookeeper() throws Exception {
        CuratorZookeeperClient zookeeperClient = curatorFramework.getZookeeperClient();
        ZooKeeper zooKeeper = zookeeperClient.getZooKeeper();
        List<String> children = zooKeeper.getChildren("/", true);
        for (String path:children){
            System.out.println(path);
        }

    }

    @Test
    public void testCuratorCreatePath() throws Exception {
        curatorFramework.create().creatingParentContainersIfNeeded()//递归创建
                .withMode(CreateMode.PERSISTENT)//持久化节点
                .forPath("/curatorPath","curator information".getBytes());

    }

    @Test
    public void testCuratorRmPath() throws Exception {
        curatorFramework.delete().deletingChildrenIfNeeded()//递归删除
                .forPath("/curatorPath");
    }
}
