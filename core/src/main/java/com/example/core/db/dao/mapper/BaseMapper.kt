package com.example.core.db.dao.mapper



abstract class BaseMapper<Model, Entity> {

    abstract fun map(model: Model) : Entity
    abstract fun reverseMap(entity: Entity) : Model

    fun map(modelList: List<Model>) : List<Entity> {
        val entityList = arrayListOf<Entity>()
        modelList.forEach {
            map(it)?.let {
                entityList.add(it)
            }
        }
        return entityList
    }

    fun reverseMap(entityList: List<Entity>) : List<Model> {
        val modelList = arrayListOf<Model>()
        entityList.forEach {
            reverseMap(it)?.let {
                modelList.add(it)
            }
        }
        return modelList
    }

}