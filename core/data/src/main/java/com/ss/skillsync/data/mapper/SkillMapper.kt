package com.ss.skillsync.data.mapper

import com.ss.skillsync.data.source.remote.model.skill.SkillData
import com.ss.skillsync.data.source.remote.model.skill.SkillLearned
import com.ss.skillsync.data.source.remote.model.skill.SkillLearnedRequest
import com.ss.skillsync.model.Skill
import com.ss.skillsync.model.SkillLevel

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
fun SkillLearned.toSkill(): Skill {
    val skillLevel = kotlin.runCatching {
        SkillLevel.valueOf(level.uppercase())
    }.getOrDefault(SkillLevel.NOCHOICE)

    return Skill(
        id = _id,
        name = skill.name,
        level = skillLevel,
        skillId = skill._id,
    )
}

fun SkillData.toSkill(): Skill {
    return Skill(
        id = _id,
        name = name,
        level = SkillLevel.NOCHOICE,
    )
}

fun Skill.toSkillLearnedRequest(): SkillLearnedRequest {
    return SkillLearnedRequest(
        skill = id,
        level = level.name.lowercase(),
    )
}