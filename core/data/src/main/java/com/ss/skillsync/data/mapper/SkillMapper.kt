package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.model.SkillDTO
import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.SkillLevel

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/8/2023.
 * TODO update mappers when schema is finalized
 */
fun SkillDTO.toDomainModel(): Skill {
    val level = SkillLevel.valueOf(level.uppercase())
    return Skill(
        id = id,
        name = name,
        level = level,
    )
}

fun Skill.toDTOModel(): SkillDTO {
    val level = level.name.lowercase()
    return SkillDTO(
        id = id,
        name = name,
        level = level,
    )
}
