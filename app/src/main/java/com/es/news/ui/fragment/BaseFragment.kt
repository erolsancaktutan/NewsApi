package com.es.news.ui.fragment

import androidx.fragment.app.Fragment
import com.es.news.utility.Utils
import javax.inject.Inject

/**

 * * * * * * * * * * * * * * * * *
 *  Created by Erol Sancaktutan  *
 *    www.erolsancaktutan.com    *
 *   erolsancaktutan@gmail.com   *
 *            11/11/2021          *
 * * * * * * * * * * * * * * * * *

 */

open class BaseFragment: Fragment() {
    @Inject
    lateinit var utils: Utils
}