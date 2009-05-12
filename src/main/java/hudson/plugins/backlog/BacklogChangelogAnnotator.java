package hudson.plugins.backlog;

import hudson.MarkupText;
import hudson.model.AbstractBuild;
import hudson.scm.ChangeLogAnnotator;
import hudson.scm.ChangeLogSet.Entry;

/**
 * {@link ChangeLogAnnotator} that picks up Backlog issue KEY.
 * 
 * @author yamamoto
 * @version $Id$
 */
public class BacklogChangelogAnnotator extends ChangeLogAnnotator {

	@Override
	public void annotate(AbstractBuild<?, ?> build, Entry change,
			MarkupText text) {
		BacklogProjectProperty bpp = build.getProject().getProperty(
				BacklogProjectProperty.class);
		if (bpp == null || bpp.projectURL == null) {
			return; // not configured
		}
		annotate(bpp.projectURL, text);
	}

	void annotate(String projectURL, MarkupText text) {
		// TODO implement
	}

}
