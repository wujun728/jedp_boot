import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * @author xuerdong
 * @date 2017/3/25 10:07
 */
public class DruidDecryptTest {
    /**
     * 数据库加密
     *
     * @throws Exception
     */
    @Test
    public void encrypt() throws Exception {
        //Biyu5YzU+6sxDRbmWEa3B2uUcImzDo0BuXjTlL505+/pTb+/0Oqd3ou1R6J8+9Fy3CYrM18nBDqf6wAaPgUGOg==
        System.out.print(ConfigTools.encrypt("123456"));
    }

    /**
     * 数据库解密
     *
     * @throws Exception
     */
    @Test
    public void decrypt() throws Exception {
        System.out.print(ConfigTools.decrypt("hWJlFESxcbAEERbtYwKcY3YfZ8n+0VgRBCt9i5DxIzUoUutONW0b+JLuhuZrLXCSEnbH1qM2sDAtY+L0jBtYAg=="));
    }

}
