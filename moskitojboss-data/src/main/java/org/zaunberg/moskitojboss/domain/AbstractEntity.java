package org.zaunberg.moskitojboss.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;

  public static final String VERSION_COLUMN = "LAST_SAVED";
  public static final String ID_COLUMN = "ID";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = ID_COLUMN)
  Long id;

  @Version
  @Column(name = VERSION_COLUMN)
  private Date version;

  // -------------- Constructors -------------------------------------------

  public AbstractEntity() {
      // NOP
  }

  // -------------- Getters -------------------------------------------------
  public Long getId() {
    return id;
  }

  public Date getVersion() {
    return version;
  }

  // -------------- Setters -------------------------------------------------
  public void setId(final Long id) {
    this.id = id;
  }

  public void setVersion(final Date version) {
    this.version = version;
  }

  // -------------- Helpers -------------------------------------------------
  /**
   * Never override these methods when using JPA/Hibernate
   */
  @Override
  public final boolean equals(final Object obj) {
      return super.equals(obj);
  }

  /**
   * Never override these methods when using JPA/Hibernate
   */
  @Override
  public final int hashCode() {
      return super.hashCode();
  }

  public String toString() {
    return "AbstractEntity[" + id + "," + version + "]";
  }
}
