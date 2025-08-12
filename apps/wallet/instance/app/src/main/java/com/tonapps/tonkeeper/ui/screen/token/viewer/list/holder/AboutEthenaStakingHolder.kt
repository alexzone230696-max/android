package com.tonapps.tonkeeper.ui.screen.token.viewer.list.holder

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.tonapps.tonkeeper.ui.screen.token.viewer.list.Item
import com.tonapps.tonkeeperx.R

class AboutEthenaStakingHolder(parent: ViewGroup) :
    Holder<Item.AboutEthenaStaking>(parent, R.layout.view_about_tsusde) {

    private val descriptionView = findViewById<AppCompatTextView>(R.id.description)

    override fun onBind(item: Item.AboutEthenaStaking) {
        descriptionView.text = item.description
    }

}