require 'rmagick'

Dir.glob('img/s3/*.jpg') do |img|
  image = Magick::ImageList.new img
  image.crop!(0, 0, 275, 275)
  image.write img
end
