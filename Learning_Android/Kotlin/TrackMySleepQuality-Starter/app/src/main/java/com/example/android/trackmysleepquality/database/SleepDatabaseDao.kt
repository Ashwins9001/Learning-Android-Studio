/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Create data access object which maps Kotlin functions to SQL queries (Room does the work)
//Room provides annotations for common func, custom ones defn via @Query
@Dao
interface SleepDatabaseDao
{
    //all SleepNights are dataclass obj, they are entities/rows within table
    //fun name(args: data type)
    @Insert
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    //Take Long key arg, return nullable SleepNight obj
    //Supply query as string param to annotation
    //Select all cols from table, where nightId matches key sent into func, return night obj
    //Use colon notation in query to ref args of func
    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight?

    //Metadata of query passed upon func call, defined in annotation therefore no body
    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    //Order row by nightId val, then take top row and return
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?

    //Return all rows as list of SleepNight entities, LiveData so it stays updated, only called once
    @Query("SELECT * FROM  daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>
}
