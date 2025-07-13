package com.catimg.android.catimg.domain.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.catimg.R
import com.catimg.domain.model.ToolWithFilter
import com.catimg.domain.tools.filters.blur.BilateralBlur
import com.catimg.domain.tools.filters.blur.BoxBlur
import com.catimg.domain.tools.filters.blur.Gaussian
import com.catimg.domain.tools.filters.blur.ZoomBlur
import com.catimg.domain.tools.filters.colorcorrect.Brightness
import com.catimg.domain.tools.filters.colorcorrect.Contrast
import com.catimg.domain.tools.filters.colorcorrect.Exposure
import com.catimg.domain.tools.filters.colorcorrect.FalseColor
import com.catimg.domain.tools.filters.colorcorrect.Gamma
import com.catimg.domain.tools.filters.colorcorrect.HighlightsAndShadows
import com.catimg.domain.tools.filters.colorcorrect.Hue
import com.catimg.domain.tools.filters.colorcorrect.Levels
import com.catimg.domain.tools.filters.colorcorrect.RGB
import com.catimg.domain.tools.filters.colorcorrect.Saturation
import com.catimg.domain.tools.filters.colorcorrect.Sharpen
import com.catimg.domain.tools.filters.colorcorrect.WhiteBalance
import com.catimg.domain.tools.filters.distortion.Bulge
import com.catimg.domain.tools.filters.distortion.Dilation
import com.catimg.domain.tools.filters.distortion.GlassSphereRefraction
import com.catimg.domain.tools.filters.distortion.SphereRefraction
import com.catimg.domain.tools.filters.distortion.Swirl
import com.catimg.domain.tools.filters.edgedetection.Laplacian
import com.catimg.domain.tools.filters.edgedetection.SobelEdgeDetection
import com.catimg.domain.tools.filters.edgedetection.ThresholdEdge
import com.catimg.domain.tools.filters.filters.ColorInversion
import com.catimg.domain.tools.filters.filters.Haze
import com.catimg.domain.tools.filters.filters.Sepia
import com.catimg.domain.tools.filters.sketch.CrossHatch
import com.catimg.domain.tools.filters.sketch.Kuwahara
import com.catimg.domain.tools.filters.stylization.CGAColorSpace
import com.catimg.domain.tools.filters.stylization.Emboss
import com.catimg.domain.tools.filters.stylization.Halftone
import com.catimg.domain.tools.filters.stylization.Pixelation
import com.catimg.domain.tools.filters.stylization.Posterize
import com.catimg.domain.tools.filters.stylization.Vignette

@Composable
private fun vector(id: Int): ImageVector {
    return ImageVector.vectorResource(id)
}

@Composable
fun getToolList(selectedSection: String): List<ToolWithFilter> {
    return when (selectedSection) {
        "Filters" -> listOf(
            ToolWithFilter(name = stringResource(R.string.haze),
                icon = vector(R.drawable.filters_haze),
                toolFilter = Haze()
            ),
            ToolWithFilter(name = stringResource(R.string.sepia),
                icon = ImageVector.vectorResource(R.drawable.filters_sepia),
                toolFilter = Sepia()
            ),
            ToolWithFilter(name = stringResource(R.string.color_inversion),
                icon = vector(R.drawable.filters_inversion),
                toolFilter = ColorInversion()
            ),
        )
        "ColorCorrect" -> listOf(
            ToolWithFilter(name = stringResource(R.string.brightness),
                icon = vector(R.drawable.color_correction_brightness),
                toolFilter = Brightness()
            ),
            ToolWithFilter(name = stringResource(R.string.contrast),
                icon = vector(R.drawable.color_correction_contrast),
                toolFilter = Contrast()
            ),
            ToolWithFilter(name = stringResource(R.string.saturation),
                icon = vector(R.drawable.color_correction_saturation),
                toolFilter = Saturation()
            ),
            ToolWithFilter(name = stringResource(R.string.levels),
                icon = vector(R.drawable.color_correction_levels),
                toolFilter = Levels()
            ),
            ToolWithFilter(name = stringResource(R.string.exposure),
                icon = vector(R.drawable.color_correction_exposure),
                toolFilter = Exposure()
            ),
            ToolWithFilter(name = stringResource(R.string.rgb),
                icon = vector(R.drawable.color_correction_rgb),
                toolFilter = RGB()
            ),
            ToolWithFilter(name = stringResource(R.string.hue),
                icon = vector(R.drawable.color_correction_hue),
                toolFilter = Hue()
            ),
            ToolWithFilter(name = stringResource(R.string.white_balance),
                icon = vector(R.drawable.color_correction_white_balance),
                toolFilter = WhiteBalance()
            ),
            ToolWithFilter(name = stringResource(R.string.false_color),
                icon = vector(R.drawable.color_correction_false),
                toolFilter = FalseColor()
            ),
            ToolWithFilter(name = stringResource(R.string.sharpen),
                icon = vector(R.drawable.color_correction_sharpen),
                toolFilter = Sharpen()
            ),
            ToolWithFilter(name = stringResource(R.string.gamma),
                icon = vector(R.drawable.color_correction_gamma),
                toolFilter = Gamma()
            ),
            ToolWithFilter(name = stringResource(R.string.highlights_shadows),
                icon = vector(R.drawable.color_correction_highlights),
                toolFilter = HighlightsAndShadows()
            ),
        )
        "Stylization" -> listOf(
            ToolWithFilter(name = stringResource(R.string.pixelate),
                icon = vector(R.drawable.stylization_pixelate),
                toolFilter = Pixelation()
            ),
            ToolWithFilter(name = stringResource(R.string.halftone),
                icon = vector(R.drawable.stylization_halftone),
                toolFilter = Halftone()
            ),
            ToolWithFilter(name = stringResource(R.string.cga),
                icon = vector(R.drawable.stylization_cga),
                toolFilter = CGAColorSpace()
            ),
            ToolWithFilter(name = stringResource(R.string.posterize),
                icon = vector(R.drawable.stylization_posterize),
                toolFilter = Posterize()
            ),
            ToolWithFilter(name = stringResource(R.string.emboss),
                icon = vector(R.drawable.stylization_hammer),
                toolFilter = Emboss()
            ),
            ToolWithFilter(name = stringResource(R.string.vignette),
                icon = vector(R.drawable.stylization_vignette),
                toolFilter = Vignette()
            ),
        )
        "Sketch Filters" -> listOf(
            ToolWithFilter(name = stringResource(R.string.crosshatch),
                icon = ImageVector.vectorResource(R.drawable.sketch_filters_crosshatch),
                toolFilter = CrossHatch()
            ),
            ToolWithFilter(name = stringResource(R.string.kuwahara),
                icon = vector(R.drawable.sketch_filters_kuwahara),
                toolFilter = Kuwahara()
            ),
        )
        "Edge Detection" -> listOf(
            ToolWithFilter(name = stringResource(R.string.sobel_edge),
                icon = vector(R.drawable.edge_detection_sobel),
                toolFilter = SobelEdgeDetection()
            ),
            ToolWithFilter(name = stringResource(R.string.threshold_sobel_edge),
                icon = vector(R.drawable.edge_detection_threshold),
                toolFilter = ThresholdEdge()
            ),
            ToolWithFilter(name = stringResource(R.string.laplacian),
                icon = vector(R.drawable.edge_detection_laplacian),
                toolFilter = Laplacian()
            ),
        )
        "Blur" -> listOf(
            ToolWithFilter(name = stringResource(R.string.gaussian_blur),
                icon = vector(R.drawable.blur_gauss),
                toolFilter = Gaussian()
            ),
            ToolWithFilter(name = stringResource(R.string.box_blur),
                icon = vector(R.drawable.blur_box),
                toolFilter = BoxBlur()
            ),
            ToolWithFilter(name = stringResource(R.string.bilateral_blur),
                icon = vector(R.drawable.blur_bilateral),
                toolFilter = BilateralBlur()
            ),
            ToolWithFilter(name = stringResource(R.string.zoom_blur),
                icon = vector(R.drawable.blur_zoom),
                toolFilter = ZoomBlur()
            ),
        )
        "Deformation" -> listOf(
            ToolWithFilter(name = stringResource(R.string.swirl_distortion),
                icon = vector(R.drawable.distortion_swirl),
                toolFilter = Swirl()
            ),
            ToolWithFilter(name = stringResource(R.string.bulge_distortion),
                icon = vector(R.drawable.distortion_bulge),
                toolFilter = Bulge()
            ),
            ToolWithFilter(name = stringResource(R.string.sphere_refraction),
                icon = vector(R.drawable.distortion_sphere),
                toolFilter = SphereRefraction()
            ),
            ToolWithFilter(name = stringResource(R.string.glass_sphere_refraction),
                icon = vector(R.drawable.distortion_glass),
                toolFilter = GlassSphereRefraction()
            ),
            ToolWithFilter(name = stringResource(R.string.dilation),
                icon = vector(R.drawable.distortion_dilation),
                toolFilter = Dilation() ),
        )
        else -> emptyList()
    }
}