package com.kazemieh.divar.core.parameter.repository

import com.kazemieh.divar.core.parameter.entity.ParameterAnswer
import org.springframework.data.jpa.repository.JpaRepository

interface ParameterAnswerRepository : JpaRepository<ParameterAnswer, Long> {


}