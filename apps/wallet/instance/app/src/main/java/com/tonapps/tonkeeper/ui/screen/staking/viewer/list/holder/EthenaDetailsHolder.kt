package com.tonapps.tonkeeper.ui.screen.staking.viewer.list.holder

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.tonapps.tonkeeper.helper.BrowserHelper
import com.tonapps.tonkeeper.ui.screen.staking.viewer.list.Item
import com.tonapps.tonkeeperx.R
import com.tonapps.uikit.color.textAccentColor
import com.tonapps.wallet.localization.Localization

class EthenaDetailsHolder(
    parent: ViewGroup,
): Holder<Item.EthenaDetails>(parent, R.layout.view_ethena_details) {

    private val apyView = findViewById<AppCompatTextView>(R.id.apy)
    private val bonusApyView = findViewById<AppCompatTextView>(R.id.bonus_apy)
    private val bonusApyContainerView = findViewById<View>(R.id.bonus_apy_container)
    private val bonusApySubtitleView = findViewById<AppCompatTextView>(R.id.bonus_apy_subtitle)

    override fun onBind(item: Item.EthenaDetails) {
        apyView.text = item.apyFormat
        if (item.bonusApyFormat != null) {
            bonusApyView.text = item.bonusApyFormat
            bonusApyContainerView.visibility = View.VISIBLE
            val moreText = getString(Localization.learn_more)
            bonusApySubtitleView.text = SpannableStringBuilder("${item.bonusDescription}\n$moreText").apply {
                setSpan(
                    ForegroundColorSpan(context.textAccentColor),
                    length - moreText.length, length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            bonusApySubtitleView.setOnClickListener {
                item.bonusUrl?.let {
                    BrowserHelper.open(context, it)
                }
            }
        } else {
            bonusApyContainerView.visibility = View.GONE
        }
    }

}