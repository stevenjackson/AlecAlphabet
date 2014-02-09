require 'watir-webdriver'
require_relative 'dictionary_writer'

class Scraper
  def scrape
    @writer = DictionaryWriter.new     'scraped.json'
    @browser = Watir::Browser.new
    ('v'..'z').each { |letter| do_letter letter }
    @browser.close
    @writer.close
  end

  def do_letter letter
    @browser.goto "http://photographicdictionary.com/#{letter}"
    scrape_page
    while next_page?
      next_page
      scrape_page
    end

  end

  def scrape_page
    @browser.divs(class: 'word').map { |div| scrape_word div }.each { |word| @writer.write word }
  end
  
  def scrape_word div
    begin 
      word = div.div(class: 'title').h2.link.text
      img_link = div.div(class: 'img-wrap').image.src
      attribution = div.div(class: 'photo').small.text
      attribution_link = div.div(class: 'photo').small.link.href
      license = div.div(class: 'license').image.alt
    rescue
    end

    { word: word, img_link: img_link, attribution: attribution, attribution_link: attribution_link, license: license }
  end

  def next_page?
    @browser.li(class: 'pager-next').exists?
  end

  def next_page
    @browser.li(class: 'pager-next').link.click
  end
end

Scraper.new.scrape
