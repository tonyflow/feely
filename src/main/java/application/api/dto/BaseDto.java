package application.api.dto;

public interface BaseDto<E extends Object> {

	public E toEntity();

	public BaseDto<E> fromEntity(E entity);
}
