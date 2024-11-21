package com.example.domain.model;

public enum MembershipLevel {
	NON_MEMBER("Non-member"),
	MEMBER("Member"),
	PREMIUM("Premium");
	
	private final String value;
	
	private MembershipLevel(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static MembershipLevel fromString(String value) {
		for(MembershipLevel level : MembershipLevel.values()) {
			if(level.getValue().equals(value)) {
				return level;
			}
		}
		throw new IllegalArgumentException("Unknown enum value: " + value);
	}

}
