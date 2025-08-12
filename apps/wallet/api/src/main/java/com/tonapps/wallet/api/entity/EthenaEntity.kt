package com.tonapps.wallet.api.entity

import android.os.Parcelable
import com.tonapps.extensions.map
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.math.BigDecimal

@Parcelize
data class EthenaEntity(
    val methods: List<Method>,
    val about: About,
) : Parcelable {

    @Parcelize
    data class About(
        val description: String,
        val tsusdeDescription: String,
        val faqUrl: String,
    ) : Parcelable {
        constructor(json: JSONObject) : this(
            description = json.getString("description"),
            tsusdeDescription = json.getString("tsusde_description"),
            faqUrl = json.getString("faq_url")
        )
    }

    @Parcelize
    data class Method(
        val type: Type,
        val name: String,
        val apy: BigDecimal,
        val bonusApy: BigDecimal?,
        val bonusDescription: String?,
        val eligibleBonusUrl: String?,
        val depositUrl: String,
        val withdrawalUrl: String,
        val jettonMaster: String,
        val links: List<String>,
    ) : Parcelable {
        enum class Type(val id: String) {
            STONFI("stonfi"),
            AFFLUENT("affluent");

            companion object {
                fun fromId(id: String): Type {
                    return entries.find { it.id.equals(id, ignoreCase = true) }
                        ?: throw IllegalArgumentException("Invalid type: $id")
                }
            }
        }

        constructor(json: JSONObject) : this(
            type = Type.fromId(json.getString("type")),
            name = json.getString("name"),
            apy = BigDecimal.valueOf(json.getDouble("apy")),
            bonusApy = json.optString("bonus_apy").takeIf { it.isNotEmpty() }?.let {
                BigDecimal(it)
            },
            bonusDescription = json.optString("bonus_description"),
            eligibleBonusUrl = json.optString("eligible_bonus_url"),
            depositUrl = json.getString("deposit_url"),
            withdrawalUrl = json.getString("withdrawal_url"),
            jettonMaster = json.getString("jetton_master"),
            links = json.optJSONArray("links")?.let { array ->
                (0 until array.length()).map { array.getString(it) }
            } ?: emptyList()
        )
    }

    constructor(json: JSONObject) : this(
        methods = json.getJSONArray("methods").map { Method(it) },
        about = About(json.getJSONObject("about"))
    )
}
