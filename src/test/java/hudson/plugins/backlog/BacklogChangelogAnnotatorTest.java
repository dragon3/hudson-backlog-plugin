package hudson.plugins.backlog;

import java.util.regex.Matcher;

import hudson.MarkupText;

import org.junit.Before;
import org.junit.Test;

public class BacklogChangelogAnnotatorTest {

	private BacklogChangelogAnnotator annotator;

	@Before
	public final void setup() {
		annotator = new BacklogChangelogAnnotator();
	}
	
	@Test
	public final void testAnnotate() {
		{
			String src = "[[BLG-123]]について、対応しました。";
			MarkupText text = new MarkupText(src);
			annotator.annotate("https://backlog.backlog.jp", text);
			System.out.println(text.toString());
		}

		{
			String src = "BLG-123について、対応しました。";
			MarkupText text = new MarkupText(src);
			annotator.annotate("https://backlog.backlog.jp", text);
			System.out.println(text.toString());
		}
	}
	
	@Test
	public final void testIssuePattern() {
		{
			String src = "[[BLG-123]]について、対応しました。";
	        Matcher m = BacklogChangelogAnnotator.ISSUE_KEY_PATTERN.matcher(src);
	        while(m.find()) {
	        	System.out.println("groupCount=" + m.groupCount());
	        	for (int i = 0; i < m.groupCount(); i++) {
	        		System.out.println(m.group(i));
	        	}
        		System.out.println(m.group(2));
	        }
		}
		{
			String src = "BLG-123について、対応しました。";
	        Matcher m = BacklogChangelogAnnotator.ISSUE_KEY_PATTERN.matcher(src);
	        while(m.find()) {
	        	System.out.println("groupCount=" + m.groupCount());
	        	for (int i = 0; i < m.groupCount(); i++) {
	        		System.out.println(m.group(i));
	        	}
	        }
		}
	}
}
