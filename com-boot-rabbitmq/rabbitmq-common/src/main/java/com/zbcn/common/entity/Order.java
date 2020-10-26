package com.zbcn.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = 2174447427263683506L;
    private int id;
    private String name;
    private String messageId;

}
