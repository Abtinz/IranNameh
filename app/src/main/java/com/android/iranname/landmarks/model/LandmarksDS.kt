package com.android.iranname.landmarks.model

import com.android.iranname.commonServices.model.CommentDC

/** this data call will save Landmarks information
 * @param image uri of landmark image
 * @param name name of landmark
 * @param description two or three lines about landmark
 * @param location location of the landmark
 */
data class LandmarksDC(
    val image:String,
    val images:List<String>,
    val name:String,
    val description:String,
    val location:String,
    val foundation:String,
    val comments:List<CommentDC>?,
    val relatedLandmarksDC: List<LandmarksDC>?
)
