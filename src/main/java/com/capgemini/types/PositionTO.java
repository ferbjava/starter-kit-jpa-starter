package com.capgemini.types;

public class PositionTO {

	private Long id;
	private String position;

	public PositionTO(Long id, String position) {
		super();
		this.id = id;
		this.position = position;
	}

	public PositionTO(String position) {
		super();
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public String getPosition() {
		return position;
	}

	public static PositionTOBuilder builder() {
		return new PositionTOBuilder();
	}

	public static class PositionTOBuilder {
		private Long id;
		private String position;

		public PositionTOBuilder() {
			super();
		}

		public PositionTOBuilder withPosition(String position) {
			this.position = position;
			return this;
		}

		public PositionTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public PositionTO build() {
			checkBeforeBuild(position);
			return new PositionTO(id, position);
		}

		private void checkBeforeBuild(String position) {
			if (position == null || position.isEmpty()) {
				throw new RuntimeException("Incorrect 'Position' to be created");
			}
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionTO other = (PositionTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionTO [id=" + id + ", position=" + position + "]";
	}

}
