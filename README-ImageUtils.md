# ImageUtils
抠图以及去边的功能适合使用ps图、自定义手写签名等，不适合拍照的图片，颜色会有差别


- imageEliminateWhite() 去除白色像素以及与白色相近的像素

- imageEliminateBlack() 去除黑色像素以及与黑色相近的像素

- setImageHighlight() 图像高亮

- scaleToTargetKB() 通过缩放图片达到指定的文件大小，可保留透明度（设定的kb值太小 可能会稍微比指定的kb大一些）

- compressToTargetKB() 通过压缩质量，不可保留透明度

- removeBoundaryByColor()  移除指定颜色的边界区域 （会自动调整边界 生成新大小的图片）

- imageConvertColor()  Bitmap转换成指定颜色，透明除外