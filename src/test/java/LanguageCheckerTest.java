import org.junit.Assert;
import org.junit.Test;
import util.validator.LanguageChecker;

import java.io.IOException;

public class LanguageCheckerTest {

    @Test
    public void testThatLanguageIsKorean() throws IOException {
        LanguageChecker languageChecker = new LanguageChecker();
        String expectedLanguage = "ko";
        String actualLanguage = languageChecker.getLanguage("이해하기 쉬운 용어로 작성된 의학 주제, 증상, 의약품, 시술, 뉴스 등에 대한 정보를 찾아보세요.").get().getLanguage();
        Assert.assertEquals(expectedLanguage, actualLanguage);
    }

    @Test
    public void testThatLanguageIsEnglish() throws IOException {
        LanguageChecker languageChecker = new LanguageChecker();
        String expectedLanguage = "en";
        String actualLanguage = languageChecker.getLanguage("Find information on medical topics, symptoms, drugs, procedures, news and more, written in everyday language.").get().getLanguage();
        Assert.assertEquals(expectedLanguage, actualLanguage);
    }

    @Test
    public void testThatLanguageIsSpanish() throws IOException {
        LanguageChecker languageChecker = new LanguageChecker();
        String expectedLanguage = "es";
        String actualLanguage = languageChecker.getLanguage("Busque información sobre temas médicos, síntomas, fármacos, procedimientos, noticias y mucho más, escrita en lenguaje cotidiano.").get().getLanguage();
        Assert.assertEquals(expectedLanguage, actualLanguage);
    }
}
