package io.battleoverflow.pdfx.resources

import android.annotation.TargetApi
import android.graphics.pdf.PdfRenderer
import android.os.Build
import android.os.ParcelFileDescriptor
import io.battleoverflow.pdfx.document.Document
import io.battleoverflow.pdfx.utils.randomID

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class DocumentRepository : Repository<Document>() {
    /**
     * Register document in repository
     * @returns document id
     */
    fun register(getPair: Pair<ParcelFileDescriptor, PdfRenderer>?): Document {
        val id = randomID
        val (fileDescriptor, renderer) = getPair!!
        val document = Document(id, renderer, fileDescriptor)
        set(id, document)
        return document
    }

    public override fun close(id: String) {
        get(id).close()
        super.close(id)
    }
}
