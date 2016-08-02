import http.helpers.TextFinder;
import org.junit.Assert;
import org.junit.Test;

public class TextFinderTest {

    @Test
    public void verifyThatSimpleTextCouldBeFound2() {
        Assert.assertTrue(TextFinder.textIsFound("abc das", "21dsabcdasas"));
    }

    @Test
    public void verifyThatSimpleTextCouldBeFound3() {
        Assert.assertTrue(TextFinder.textIsFound("Level 4", "21dsabc@/asaslevel$%sdfg4"));
    }

    @Test
    public void verifyThatSimpleTextCouldBeFound4() {
        Assert.assertTrue(TextFinder.textIsFound("Try Again", " gh  hgh  6%^7 hjgasd 7^& dfg!44dbTRYAGAIN#%hjkh  jkhjkhjkh ^&(* hjkhk"));
    }

    @Test
    public void verifyThatSimpleTextCouldBeFound5() {
        Assert.assertTrue(TextFinder.textIsFound("Try Again", " gh  hgh  6%^7 hjgasd 7^& dfg!44dbTRY@GAIN#%hjkh  jkhjkhjkh ^&(* hjkhk"));
    }

    @Test
    public void verifyThatSimpleTextCouldBeFound6() {
        Assert.assertTrue(TextFinder.textIsFound("Try Again", " gh  hgh  6%^7 hjgasd 7^& dfg!44d T@Y AGAIN#%hjk"));
    }

    @Test
    public void verifyThatSimpleTextCouldBeFound7() {
        Assert.assertTrue(TextFinder.textIsFound("Try Again", " gh  thgh  6%^7 hjgasd 7^& dfg!44d T@Y GAIN#%hjk"));
    }

    @Test
    public void verifyThatSimpleTextCouldBeFound8() {
        Assert.assertTrue(TextFinder.textIsFound("Try Again", " gh  try gh  6%^7 hjgasd 7^& dfg!44d T@y GaIN#%hjk in"));
    }

    @Test
    public void verifyThatSimpleTextShouldNotBeFound1() {
        Assert.assertFalse(TextFinder.textIsFound("Tryagain", "21dsabc againdasas"));
    }

    @Test
    public void verifyThatSimpleTextShouldNotBeFound2() {
        Assert.assertFalse(TextFinder.textIsFound("Try Again", " gh  t$y gh  6%^7 hjgasd 7^& dfg!44d T@Y GIN#%hjk in"));
    }

}