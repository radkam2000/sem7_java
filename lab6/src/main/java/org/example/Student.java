package org.example;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private @NonNull String name;
    private @NonNull String surname;
    private @NonNull Integer index;
    private @NonNull String email;
    private @NonNull String address;
    private @NonNull Date birthDate;

}
