package hudson.plugins.backlog;

import hudson.model.AbstractProject;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

/**
 * Property for {@link AbstractProject} that stores the associated Backlog website URL.
 *
 * @author dragon3
 */
public final class BacklogProjectProperty extends JobProperty<AbstractProject<?,?>> {

    public final String projectURL;

    @DataBoundConstructor
    public BacklogProjectProperty(String projectURL) {
        // normalize
        if(projectURL ==null || projectURL.length()==0)
            projectURL=null;
        else {
            if(!projectURL.endsWith("/"))
                projectURL += '/';
        }
        this.projectURL = projectURL;
    }
    
    public DescriptorImpl getDescriptor() {
        return DESCRIPTOR;
    }

    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static final class DescriptorImpl extends JobPropertyDescriptor {

        public DescriptorImpl() {
            super(BacklogProjectProperty.class);
            load();
        }

        public boolean isApplicable(Class<? extends Job> jobType) {
            return AbstractProject.class.isAssignableFrom(jobType);
        }

        public String getDisplayName() {
            return "Associated Trac website";
        }

        @Override
        public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException {
            BacklogProjectProperty bpp = req.bindJSON(BacklogProjectProperty.class,formData);
            if(bpp.projectURL==null)
                bpp = null; // not configured
            return bpp;
        }
    }
}
