import environment.EnvironmentFactory;
import http.helpers.EnvironmentHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentFactoryTest {

    @Test
    public void testThatEnvironmentPropertiesCouldBeSet(){
        Map<String, String> map = new HashMap<>();
        map.put("IS_LOCAL", "TRUE");
        EnvironmentHelper.setEnv(map);
        Assert.assertTrue(EnvironmentFactory.isLocal());
        Assert.assertFalse(EnvironmentFactory.isMobile());
        Assert.assertFalse(EnvironmentFactory.isRemote());
        Assert.assertFalse(EnvironmentFactory.isHeadless());

        map.clear();
        map.put("IS_MOBILE", "TRUE");
        EnvironmentHelper.setEnv(map);
        Assert.assertFalse(EnvironmentFactory.isLocal());
        Assert.assertTrue(EnvironmentFactory.isMobile());
        Assert.assertFalse(EnvironmentFactory.isRemote());
        Assert.assertFalse(EnvironmentFactory.isHeadless());

        map.clear();
        map.put("IS_REMOTE", "TRUE");
        EnvironmentHelper.setEnv(map);
        Assert.assertFalse(EnvironmentFactory.isLocal());
        Assert.assertFalse(EnvironmentFactory.isMobile());
        Assert.assertTrue(EnvironmentFactory.isRemote());
        Assert.assertFalse(EnvironmentFactory.isHeadless());


        map.clear();
        map.put("IS_HEADLESS", "TRUE");
        EnvironmentHelper.setEnv(map);
        Assert.assertFalse(EnvironmentFactory.isLocal());
        Assert.assertFalse(EnvironmentFactory.isMobile());
        Assert.assertFalse(EnvironmentFactory.isRemote());
        Assert.assertTrue(EnvironmentFactory.isHeadless());
    }
}
