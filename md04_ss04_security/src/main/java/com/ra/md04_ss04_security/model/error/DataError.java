package com.ra.md04_ss04_security.model.error;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class DataError<T>{
    private T message;
    private int code;
}
