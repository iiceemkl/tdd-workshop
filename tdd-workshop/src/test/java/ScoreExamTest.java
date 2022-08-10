import ScoreExamCalculator.ScoreExamCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Should calculate score exam from provide data")
public class ScoreExamTest {

    static ScoreExamCalculator scoreExamCalculator;

    @BeforeAll
    static void setUp() {
        scoreExamCalculator = new ScoreExamCalculator();
    }

    @ParameterizedTest
    @CsvSource({"80,A", "70,B", "60,C", "50,D", "45,F", "63,C"})
    void itShouldCalculateGradeFollowingScoreProperly(int score, String grade) {
        assertEquals(grade, scoreExamCalculator.grade(score));
    }
}