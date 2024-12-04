package com.example.testboard.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/*
 * @Configuration : 스프링부트 환경설정 클래스임을 명시, 자동으로 빈 등록.
 * 이 애너테이션이 붙게되면 @ComponentScan 이 스캔할 때 이 클래스에 @Bean으로 지정한 모든 빈들도 IoC(Inversion of Control) 컨테이너에 등록.
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class DbConfiguration {

    /*
     * Hikari 설정 (1)
     *
     * @Bean : return 되는 객체를 IoC 컨테이너에 등록.
     * 특별히 지정하는 이름이 없다면 IoC 컨테이너에 해당 메서드명으로 등록. 물론 이름 지정도 간으. 보통은 메서드명으로 등록. 중복안됨.
     * application.properties 파일로 부터 데이터베이스 관련된 정보를 읽어와서 히카리 설정 객체를 리턴.
     * 접두어는 해당 접두어로 시작하는 정보들을 읽어온다는 뜻임.
     */
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    /*
     * Hikari 설정 (2)
     *
     * 히카리 설정 객체(HikariConfig) 를 넘겨받아서 DataSource 객체를 리턴.
     * 이 단계에서 데이터베이스 연결이 완성.
     * 만약 아이디나 패스워드가 틀렸다면 당연히 이 단계에서 오류가 발생. 다시금 application.properties 파일 체크.
     * DB 연결이 잘 되었는지 확인해보기 위해서 콘솔에 dataSource 객체를 toString() 메서드로 출력.
     * 히카리풀 뒤에 숫자가 붙어나옴 --> HikariDataSource (HikariPool-1)
     * 이 담계를 통해 HikariCP(Connection pool) 연결이 완성.
     * 이제 DB 연결이 필요한 부분에서 이 dataSource 를 가지고 연결해주면 됨.
     */
    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());

        return dataSource;
    }

    // MyBatis 설정 (1) : SqlSessionFactory <-- SqlSessionFactoryBean
//    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        return sqlSessionFactoryBean.getObject();
    }

    // MyBatis 설정 (2) : SqlSessionTemplate <-- SqlSessionFactory
//    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}
