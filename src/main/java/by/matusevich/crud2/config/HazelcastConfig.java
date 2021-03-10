package by.matusevich.crud2.config;

import com.hazelcast.config.*;
import com.hazelcast.config.cp.FencedLockConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import info.jerrinot.subzero.SubZero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance instance(){
        return Hazelcast.newHazelcastInstance(hazelCastConfig());
    }

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setNetworkConfig(getHazelcastNetworkConfig());
        config.setClusterName("hazelcast-cluster");
        config.setInstanceName("hazelcast-template");
        config.setPartitionGroupConfig(getPartitionGroupConfig());
        config.setProperty("hazelcast.health.monitoring.level", "OFF");

        SubZero.useAsGlobalSerializer(config);
        config.getCPSubsystemConfig()
                .setCPMemberCount(3)
                .addLockConfig(new FencedLockConfig("reentrant-lock", 0))
                .addLockConfig(new FencedLockConfig("limited-reentrant-lock", 10))
                .addLockConfig(new FencedLockConfig("non-reentrant-lock", 1));

        return config;
    }

    private NetworkConfig getHazelcastNetworkConfig() {

        NetworkConfig networkConfig = new NetworkConfig()
//                .setPort(5900)
//                .setPortAutoIncrement(false)
                ;

        JoinConfig joinConfig = new JoinConfig();
        TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setConnectionTimeoutSeconds(30);
        tcpIpConfig.setEnabled(true);

        List<String> memberList = new ArrayList<>();
        memberList.add("127.0.0.1:5701");
        tcpIpConfig.setMembers(memberList);

        joinConfig.setTcpIpConfig(tcpIpConfig);

        MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setMulticastTimeoutSeconds(30);
        multicastConfig.setMulticastTimeToLive(255);
        multicastConfig.setEnabled(false);

        joinConfig.setMulticastConfig(multicastConfig);

        networkConfig.setJoin(joinConfig);

        return networkConfig;
    }

    private PartitionGroupConfig getPartitionGroupConfig() {

        return new PartitionGroupConfig()
                .setEnabled(true)
                .setGroupType(PartitionGroupConfig.MemberGroupType.PER_MEMBER);

    }
}

