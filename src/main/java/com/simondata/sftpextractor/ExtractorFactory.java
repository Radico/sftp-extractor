package com.simondata.sftpextractor;


import com.simondata.sftpextractor.clients.*;

/**
 * Factory to create Extractor instances
 */
public class ExtractorFactory {

    public static AbstractExtractor create(ParamsHolder paramsHolder) {
        ExtractorEngine extractorEngine = ExtractorEngine.byName(paramsHolder.getExtractorType());
        paramsHolder.setExtractorEngine(extractorEngine);
        return ExtractorFactory.makeExtractor(extractorEngine, paramsHolder);
    }

    /**
     * Factory method to build an extractor by engine type.
     * @param extractorEngine the type of extractor to build
     * @param paramsHolder params to pass to create extractors/clients
     * @return AbstractExtractor
     */
    public static AbstractExtractor makeExtractor(
            ExtractorEngine extractorEngine, ParamsHolder paramsHolder) {
        AbstractExtractor extractor = null;
        switch (extractorEngine) {
            case SFTP:
                extractor = new SFTPExtractor(paramsHolder);
                break;
            case SQL:
                extractor = new SQLExtractor(paramsHolder);
                break;
            default:
                throw new AssertionError("Extraction type not supported.");
        }
        return extractor;
    }
}
