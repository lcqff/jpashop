package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
private LocalDateTime createdDate;
private LocalDateTime lastModifiedDate;
}
