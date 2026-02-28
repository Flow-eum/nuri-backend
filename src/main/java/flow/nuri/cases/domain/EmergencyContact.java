package flow.nuri.cases.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmergencyContact {
    private String protectorName;
    private String relation;
    private String protectorPhoneNumber;
}
