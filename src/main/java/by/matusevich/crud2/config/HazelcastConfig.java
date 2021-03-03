package by.matusevich.crud2.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HazelcastConfig {
//    [192.168.247.201]:5701

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setNetworkConfig(getHazelcastNetworkConfig());
        config.setClusterName("hazelcast-cluster");
        config.setInstanceName("hazelcast-template");
        config.setPartitionGroupConfig(getPartitionGroupConfig());
        config.setProperty("hazelcast.health.monitoring.level", "NOISY");
//        config.addMapConfig(getHazelcastMapConfig());

        return config;
    }

    private NetworkConfig getHazelcastNetworkConfig() {

        NetworkConfig networkConfig = new NetworkConfig().setPort(5900)
                .setPortAutoIncrement(false);

        JoinConfig joinConfig = new JoinConfig();
        TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setConnectionTimeoutSeconds(30);
        tcpIpConfig.setEnabled(true);

        List<String> memberList = new ArrayList<>();
        memberList.add("127.0.0.1:5703");
        tcpIpConfig.setMembers(memberList);

        joinConfig.setTcpIpConfig(tcpIpConfig);

        //------------------------------------------

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
                .setGroupType(PartitionGroupConfig.MemberGroupType.PER_MEMBER)
                ;

    }

    private MapConfig getHazelcastMapConfig() {

        MapConfig mapConfig = new MapConfig();

        mapConfig.setName("hazelcast-map-config");
        mapConfig.setBackupCount(2);
        mapConfig.setAsyncBackupCount(1);

        mapConfig.setMaxIdleSeconds(3600);
        mapConfig.setTimeToLiveSeconds(3600);
        EvictionConfig evictionConfig = new EvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.LRU);
        evictionConfig.setMaxSizePolicy(MaxSizePolicy.PER_PARTITION);
        evictionConfig.setSize(1000);

        mapConfig.setEvictionConfig(evictionConfig);

        mapConfig.setMetadataPolicy(MetadataPolicy.CREATE_ON_UPDATE);
        mapConfig.setReadBackupData(false);

        mapConfig.addEntryListenerConfig(
                new EntryListenerConfig("com.justayar.springboot.util.MapEntryListener",
                        true, false));

        return mapConfig;


    }
}

