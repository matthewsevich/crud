package by.matusevich.crud2.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class NewConfig {
//
//    @Bean
//    public Config hazelConfig() {
//        Config config = new Config();
//        NetworkConfig network = config.getNetworkConfig();
//        network.setPort(5701).setPortCount(1);
//        network.setPortAutoIncrement(false);
//        JoinConfig joinConfig = config.getNetworkConfig().getJoin();
//        joinConfig.getMulticastConfig().setEnabled(false);
//        joinConfig.getAwsConfig().setEnabled(false);
//        joinConfig.getTcpIpConfig().setEnabled(true);
//        config
//                // If your configuration defines an instance name, Spring Boot tries to locate an existing instance rather than creating a new one.
//                // This name is used in different scenarios, such as identifying the hazelcast instance when running multiple instances in the same JVM.
//                .setInstanceName("dev")
//                .setClusterName("dev"); //// имя группы в конфиг
//
//    }
//}