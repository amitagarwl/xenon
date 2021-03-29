package com.github.amitagarwl;

import com.github.amitagarwl.config.Config;
import com.github.amitagarwl.dao.MyDao;
import com.github.amitagarwl.utils.RedisDao;
import com.github.amitagarwl.utils.Route;
import com.github.vivekkothari.YamlParser;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.skife.jdbi.v2.DBI;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;

@Slf4j
public class BaseTest {

    public static Config config;
    public static Response response;
    public static Route route;
    public static RedisDao redisDao;
    public static MyDao dao;

    @BeforeSuite
    public void setup() throws FileNotFoundException {

        InputStream in = new FileInputStream(new File("src/test/resources/automation.yml"));
        config = YamlParser.load(in,Config.class);
        log.info("================== Starting Automation ===================");

        /* Setting all the routes */
        route = new Route(config);

        /* Setting up on demand mysql connection */
        DBI dbi = new DBI(config.getMasterDbConfig().getUrl(), config.getMasterDbConfig().getUsername(),
                config.getMasterDbConfig().getPassword());
//        dao = dbi.open(MyDao.class);

    }


    @AfterSuite
    public void cleanup() {

//        dao.close();
        log.info("Finishing test case Execution!");
        log.info("================== Finished Automation ===================");



    }








}
