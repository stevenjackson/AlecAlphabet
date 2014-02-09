require 'json'
require 'fileutils'

dict = JSON.parse File.read('dictionary.json')
dict.each do |word|
  filename = File.basename word['img_link']
  FileUtils.cp "img/#{filename}", "img/s3/#{filename}"
end
