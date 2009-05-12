package hudson.plugins.backlog;

import hudson.Plugin;
import hudson.model.Jobs;

/**
 * @author dragon3
 */
public class PluginImpl extends Plugin {

	private final BacklogChangelogAnnotator annotator = new BacklogChangelogAnnotator();

	@Override
	public void start() throws Exception {
		annotator.register();
		Jobs.PROPERTIES.add(BacklogProjectProperty.DESCRIPTOR);
		// RepositoryBrowsers.LIST.add(BacklogRepositoryBrowser.DESCRIPTOR);
	}

	@Override
	public void stop() throws Exception {
		annotator.unregister();
	}
}
