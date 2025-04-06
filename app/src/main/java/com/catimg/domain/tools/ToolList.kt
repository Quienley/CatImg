package com.catimg.android.catimg.domain.tools

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
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
            ToolWithFilter(name = "Haze",
                icon = vector(R.drawable.filters_haze),
                toolFilter = Haze()
            ),
            ToolWithFilter(name = "Sepia",
                icon = ImageVector.vectorResource(R.drawable.filters_sepia),
                toolFilter = Sepia()
            ),
            ToolWithFilter(name = "Color Inversion",
                icon = vector(R.drawable.filters_inversion),
                toolFilter = ColorInversion()
            ),
        )
        "ColorCorrect" -> listOf(
            ToolWithFilter(name = "Brightness",
                icon = vector(R.drawable.color_correction_brightness),
                toolFilter = Brightness()
            ),
            ToolWithFilter(name = "Contrast",
                icon = vector(R.drawable.color_correction_contrast),
                toolFilter = Contrast()
            ),
            ToolWithFilter(name = "Saturation",
                icon = vector(R.drawable.color_correction_saturation),
                toolFilter = Saturation()
            ),
            ToolWithFilter(name = "Levels",
                icon = vector(R.drawable.color_correction_levels),
                toolFilter = Levels()
            ),
            ToolWithFilter(name = "Exposure",
                icon = vector(R.drawable.color_correction_exposure),
                toolFilter = Exposure()
            ),
            ToolWithFilter(name = "RGB",
                icon = vector(R.drawable.color_correction_rgb),
                toolFilter = RGB()
            ),
            ToolWithFilter(name = "Hue",
                icon = vector(R.drawable.color_correction_hue),
                toolFilter = Hue()
            ),
            ToolWithFilter(name = "White Balance",
                icon = vector(R.drawable.color_correction_white_balance),
                toolFilter = WhiteBalance()
            ),
            ToolWithFilter(name = "False Color",
                icon = vector(R.drawable.color_correction_false),
                toolFilter = FalseColor()
            ),
            ToolWithFilter(name = "Sharpen",
                icon = vector(R.drawable.color_correction_sharpen),
                toolFilter = Sharpen()
            ),
            ToolWithFilter(name = "Gamma",
                icon = vector(R.drawable.color_correction_gamma),
                toolFilter = Gamma()
            ),
            ToolWithFilter(name = "Highlights and Shadows",
                icon = vector(R.drawable.color_correction_highlights),
                toolFilter = HighlightsAndShadows()
            ),
        )
        "Stylization" -> listOf(
            ToolWithFilter(name = "Pixelate",
                icon = vector(R.drawable.stylization_pixelate),
                toolFilter = Pixelation()
            ),
            ToolWithFilter(name = "Halftone",
                icon = vector(R.drawable.stylization_halftone),
                toolFilter = Halftone()
            ),
            ToolWithFilter(name = "CGA Colorspace",
                icon = vector(R.drawable.stylization_cga),
                toolFilter = CGAColorSpace()
            ),
            ToolWithFilter(name = "Posterize",
                icon = vector(R.drawable.stylization_posterize),
                toolFilter = Posterize()
            ),
            ToolWithFilter(name = "Emboss Filter",
                icon = vector(R.drawable.stylization_hammer),
                toolFilter = Emboss()
            ),
            ToolWithFilter(name = "Vignette",
                icon = vector(R.drawable.stylization_vignette),
                toolFilter = Vignette()
            ),
        )
        "Sketch Filters" -> listOf(
            ToolWithFilter(name = "Crosshatch",
                icon = ImageVector.vectorResource(R.drawable.sketch_filters_crosshatch),
                toolFilter = CrossHatch()
            ),
            ToolWithFilter(name = "Kuwahara Filter",
                icon = vector(R.drawable.sketch_filters_kuwahara),
                toolFilter = Kuwahara()
            ),
        )
        "Edge Detection" -> listOf(
            ToolWithFilter(name = "Sobel Edge Detection",
                icon = vector(R.drawable.edge_detection_sobel),
                toolFilter = SobelEdgeDetection()
            ),
            ToolWithFilter(name = "Threshold Sobel Edge Detection",
                icon = vector(R.drawable.edge_detection_threshold),
                toolFilter = ThresholdEdge()
            ),
            ToolWithFilter(name = "Laplacian ",
                icon = vector(R.drawable.edge_detection_laplacian),
                toolFilter = Laplacian()
            ),
        )
        "Blur" -> listOf(
            ToolWithFilter(name = "Gaussian Blur",
                icon = vector(R.drawable.blur_gauss),
                toolFilter = Gaussian()
            ),
            ToolWithFilter(name = "Box Blur",
                icon = vector(R.drawable.blur_box),
                toolFilter = BoxBlur()
            ),
            ToolWithFilter(name = "Bilateral Blur",
                icon = vector(R.drawable.blur_bilateral),
                toolFilter = BilateralBlur()
            ),
            ToolWithFilter(name = "Zoom Blur",
                icon = vector(R.drawable.blur_zoom),
                toolFilter = ZoomBlur()
            ),
        )
        "Deformation" -> listOf(
            ToolWithFilter(name = "Swirl Distortion",
                icon = vector(R.drawable.distortion_swirl),
                toolFilter = Swirl()
            ),
            ToolWithFilter("Bulge Distortion",
                icon = vector(R.drawable.distortion_bulge),
                toolFilter = Bulge()
            ),
            ToolWithFilter("Sphere Refraction",
                icon = vector(R.drawable.distortion_sphere),
                toolFilter = SphereRefraction()
            ),
            ToolWithFilter("Glass Sphere Refraction",
                icon = vector(R.drawable.distortion_glass),
                toolFilter = GlassSphereRefraction()
            ),
            ToolWithFilter("Dilation",
                icon = vector(R.drawable.distortion_dilation),
                toolFilter = Dilation() ),
        )
        else -> emptyList()
    }
}