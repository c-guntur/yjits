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
//FIX ME: 6. Convert to record
public record Session(String sessionTitle, String sessionAbstract, Speaker mainSpeakerModerator) {
}
