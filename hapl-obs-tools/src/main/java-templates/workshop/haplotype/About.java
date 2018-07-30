package workshop.haplotype;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.PrintStream;

/**
 * About.
 */
abstract class About {
    private static final String ARTIFACT_ID = "${project.artifactId}";
    private static final String BUILD_TIMESTAMP = "${dev.build.timestamp}";
    private static final String COMMIT = "${git.commit.id}";
    private static final String COPYRIGHT = "Copyright: ";
    private static final String LICENSE = "License: ";
    private static final String VERSION = "${project.version}";

    /**
     * Return the artifact id.
     *
     * @return the artifact id
     */
    public String artifactId() {
        return ARTIFACT_ID;
    }

    /**
     * Return the build timestamp.
     *
     * @return the build timestamp
     */
    public String buildTimestamp() {
        return BUILD_TIMESTAMP;
    }

    /**
     * Return the last commit.
     *
     * @return the last commit
     */
    public String commit() {
        return COMMIT;
    }

    /**
     * Return the copyright.
     *
     * @return the copyright
     */
    public String copyright() {
        return COPYRIGHT;
    }

    /**
     * Return the license.
     *
     * @return the license
     */
    public String license() {
        return LICENSE;
    }

    /**
     * Return the version.
     *
     * @return the version
     */
    public String version() {
        return VERSION;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(artifactId());
        sb.append(" ");
        sb.append(version());
        sb.append("\n");
        sb.append("Commit: ");
        sb.append(commit());
        sb.append("  Build: ");
        sb.append(buildTimestamp());
        sb.append("\n");
        sb.append(copyright());
        sb.append("\n");
        sb.append(license());
        sb.append("\n");
        sb.append(addText());
        return sb.toString();
    }
    
    public abstract String addText();


    /**
     * Write about text to the specified print stream.
     *
     * @param out print stream to write about text to
     */
    public void about(final PrintStream out) {
        checkNotNull(out);
        out.print(toString());
    }
}
