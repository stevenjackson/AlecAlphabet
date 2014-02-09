require 'json'
require_relative 'dictionary_writer'

class DictionaryBuilder

  def start
    @writer = DictionaryWriter.new     'dictionary.json'
    @full_dictionary = JSON.parse File.read('full_dictionary.json')
    @full_dictionary.each do |word|
      store word
    end
  end

  def prompt word
    print "#{word['word']}(#{word['license']})? "
    gets
  end

  def store word
    @writer.write update_image_link word
  end

  def update_image_link word
    file = File.basename word['img_link']
    word['img_link'] = "https://s3.amazonaws.com/alecalphabet/#{file}"
    word
  end

end
