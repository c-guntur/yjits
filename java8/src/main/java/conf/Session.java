package conf;

/**
 * Replace the entire class with a more modern Record.
 * This replaces the need for a constructor, getters
 * (no setters since the Session declares all its
 * attributes as final). A toString(), equals() and
 * a hashcode() method are also implicitly generated
 * for the record.
 * <p>
 * NOTE: This example highlights the usage of a record to replace the
 *       boilerplate of a plain Java class.
 */
//FIXME: 5a. Convert to record
public class Session {
    private final String sessionTitle;
    private final String sessionAbstract;
    private final Speaker mainSpeakerModerator;

    public Session(String sessionTitle, String sessionAbstract, Speaker mainSpeakerModerator) {
        this.sessionTitle = sessionTitle;
        this.sessionAbstract = sessionAbstract;
        this.mainSpeakerModerator = mainSpeakerModerator;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public String getSessionAbstract() {
        return sessionAbstract;
    }

    public Speaker getMainSpeakerModerator() {
        return mainSpeakerModerator;
    }

    @Override
    public String toString() {
        return "\n\n\tSession{" +
                "sessionTitle='" + sessionTitle + '\'' +
                ", sessionAbstract='" + sessionAbstract + '\'' +
                ", mainSpeakerModerator=" + mainSpeakerModerator.lastName + ", " + mainSpeakerModerator.firstName +
                '}';
    }
}
