package hudson.plugins.backlog;

import hudson.Plugin;
import hudson.model.Jobs;

/**
 * @author Kohsuke Kawaguchi
 * @plugin
 */
public class PluginImpl extends Plugin {
	private BacklogChangelogAnnotator annotator = new BacklogChangelogAnnotator();
	
	@Override
	public void start() throws Exception {
        Jobs.PROPERTIES.add(BacklogProjectProperty.DESCRIPTOR);
        annotator.register();
    }
	@Override
	public void stop() throws Exception {
        annotator.unregister();
	}
	
}
